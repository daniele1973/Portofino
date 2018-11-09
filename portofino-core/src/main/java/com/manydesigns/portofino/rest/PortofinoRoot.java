package com.manydesigns.portofino.rest;

import com.manydesigns.elements.ElementsThreadLocals;
import com.manydesigns.portofino.dispatcher.Resource;
import com.manydesigns.portofino.dispatcher.ResourceResolver;
import com.manydesigns.portofino.dispatcher.Root;
import com.manydesigns.portofino.i18n.TextProviderBean;
import com.manydesigns.portofino.pageactions.AbstractPageAction;
import com.manydesigns.portofino.pageactions.PageAction;
import com.manydesigns.portofino.pageactions.ActionContext;
import com.manydesigns.portofino.pageactions.PageInstance;
import com.manydesigns.portofino.pages.Page;
import com.manydesigns.portofino.pages.PageLogic;
import com.manydesigns.portofino.security.AccessLevel;
import com.manydesigns.portofino.security.RequiresPermissions;
import com.manydesigns.portofino.shiro.SecurityUtilsBean;
import ognl.OgnlContext;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.vfs2.FileObject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PortofinoRoot extends Root implements PageAction {

    @Context
    protected ServletContext servletContext;

    @Context
    protected HttpServletResponse response;

    @Context
    protected UriInfo uriInfo;

    @Autowired
    public Configuration portofinoConfiguration;

    protected ActionContext context;
    protected PageInstance pageInstance;

    protected static final ConcurrentMap<String, FileObject> children = new ConcurrentHashMap<>();

    protected PortofinoRoot(FileObject location, ResourceResolver resourceResolver) {
        super(location, resourceResolver);
    }

    public static PortofinoRoot get(FileObject location, ResourceResolver resourceResolver) throws Exception {
        Root root = Root.get(location, resourceResolver);
        if (!(root instanceof PortofinoRoot)) {
            if(!root.getClass().equals(Root.class)) {
                logger.warn(root + " defined in " + location + " does not extend PortofinoRoot, ignoring");
            }
            root = new PortofinoRoot(location, resourceResolver);
        }
        return (PortofinoRoot) root;
    }

    @Override
    @Path("{pathSegment}")
    public Object consumePathSegment(@PathParam("pathSegment") String pathSegment) {
        logger.debug("Publishing securityUtils in OGNL context");
        OgnlContext ognlContext = ElementsThreadLocals.getOgnlContext();
        ognlContext.put("securityUtils", new SecurityUtilsBean());
        logger.debug("Publishing textProvider in OGNL context");
        ognlContext.put("textProvider", new TextProviderBean(ElementsThreadLocals.getTextProvider()));
        FileObject child = children.get(pathSegment);
        if(child != null) {
            return consumePathSegment(pathSegment, child, resourceResolver);
        }
        return super.consumePathSegment(pathSegment);
    }

    @Override
    protected void initSubResource(Resource resource) {
        super.initSubResource(resource);
        if(resource instanceof PageAction) {
            AbstractPageAction.initPageAction((PageAction) resource, getPageInstance(), uriInfo);
        }
    }

    @Override
    public PortofinoRoot init() {
        super.init();
        Page rootPage = PageLogic.getPage(location);
        PageInstance pageInstance = new PageInstance(null, location, rootPage, getClass());
        setPageInstance(pageInstance);
        HttpServletRequest request = ElementsThreadLocals.getHttpServletRequest();
        ActionContext context = new ActionContext();
        context.setServletContext(servletContext);
        context.setRequest(request);
        context.setResponse(response);
        context.setActionPath("/");
        setContext(context);
        return this;
    }

    @Override
    public ActionContext getContext() {
        return context;
    }

    @Override
    public void setContext(ActionContext context) {
        this.context = context;
    }

    @Override
    public PageInstance getPageInstance() {
        return pageInstance;
    }

    @Override
    public void setPageInstance(PageInstance pageInstance) {
        this.pageInstance = pageInstance;
    }

    @Override
    public void prepareForExecution() {}

    @Override
    public PageAction getParent() {
        return null;
    }

    public static void mount(FileObject fileObject) {
        FileObject previous = children.putIfAbsent(getDefaultMountPointName(fileObject), fileObject);
        if(previous != null) {
            throw new RuntimeException("Already mounted: " + previous);
        }
    }

    public static void mount(FileObject fileObject, String name) {
        FileObject previous = children.putIfAbsent(name, fileObject);
        if(previous != null && !previous.equals(fileObject)) {
            throw new RuntimeException("Already mounted: " + previous);
        }
    }

    public static String getDefaultMountPointName(FileObject fileObject) {
        return fileObject.getName().getBaseName();
    }

    public static FileObject unmount(String child) {
        return children.remove(child);
    }

    public static boolean unmount(FileObject object) {
        return children.remove(getDefaultMountPointName(object), object);
    }

    /**
     * Returns a description of the root.
     * @since 5.0
     * @return the page's description as JSON.
     */
    @Path(":description")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @RequiresPermissions(level = AccessLevel.NONE)
    public Map<String, Object> getJSONDescription() {
        Map<String, Object> description = new HashMap<String, Object>();
        description.put("superclass", getClass().getSuperclass().getName());
        description.put("class", getClass().getName());
        description.put("page", pageInstance.getPage());
        description.put("path", getPath());
        description.put("children", getSubResources());
        description.put("loginPath", portofinoConfiguration.getString("login.path"));
        return description;
    }
}

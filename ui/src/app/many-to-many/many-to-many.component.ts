import {Component, Input, OnInit} from '@angular/core';
import {PortofinoComponent} from "../portofino-app.component";
import {PortofinoService} from "../portofino.service";
import {HttpClient} from "@angular/common/http";
import {FormControl} from "@angular/forms";
import {MatSnackBar} from "@angular/material";
import {Button, Operation, Page, PageConfiguration} from "../page.component";

@Component({
  selector: 'portofino-many-to-many',
  templateUrl: './many-to-many.component.html',
  styleUrls: ['./many-to-many.component.css']
})
@PortofinoComponent({ name: 'manyToMany' })
export class ManyToManyComponent extends Page implements OnInit {

  @Input()
  configuration: PageConfiguration & any;
  sourceUrl: string;
  saveEnabled: boolean;
  keySelector = new FormControl();
  keys: Key[];
  key: Key;
  associations: Association[];

  readonly associationsPath = "/:availableAssociations";

  constructor(protected http: HttpClient, public portofino: PortofinoService, private snackBar: MatSnackBar) {
    super(portofino, http);
  }

  ngOnInit() {
    this.keySelector.valueChanges.subscribe(value => {
      this.key = value;
      const onePk = value.key;
      this.http.get<any>(this.sourceUrl + this.associationsPath + '/' + onePk).subscribe(assocs => {
        this.associations = [];
        let keys = assocs.schema.properties[onePk].items.enum;
        keys.forEach(k => {
          let key = new Key();
          key.key = k;
          key.label = assocs.form[0].titleMap[k];
          let association = new Association();
          association.key = key;
          association.selected = assocs.model[onePk].includes(k);
          this.associations.push(association);
        });
      });
    });
    this.sourceUrl = this.computeSourceUrl();
    this.http.get<Operation[]>(this.sourceUrl + this.operationsPath).subscribe(ops => {
      this.saveEnabled = this.operationAvailable(ops,"POST");
    });
    this.http.get<Keys>(this.sourceUrl).subscribe(keys => {
      this.keys = keys.keys;
      if(this.keys.length == 1) {
        this.keySelector.setValue(this.keys[0]);
      }
    });
  }

  @Button({
    icon: "save",
    text: "Save",
    color: "primary",
    presentIf: (self) => self.saveEnabled,
    enabledIf: (self) => self.key
  })
  save() {
    if(!this.key) {
      return;
    }
    let body = {};
    body[this.key.key] = this.associations.filter(a => a.selected).map(a => a.key.key);
    this.http.post(this.sourceUrl, body).subscribe(_ => {
      this.snackBar.open('Saved', null, { duration: 10000, verticalPosition: 'top' });
    }, error => {
      this.snackBar.open('Error', null, { duration: 10000, verticalPosition: 'top' });
    });
  }
}

class Keys {
  keys: Key[];
}

class Key {
  key: string;
  label: string;
}

class Association {
  key: Key;
  selected: boolean;
}

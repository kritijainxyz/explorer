import { Component, OnInit } from '@angular/core';
import {FileService} from "../../services/file.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FileMenuComponent} from "../file-menu/file-menu.component";

@Component({
  selector: 'app-create-file-popup',
  templateUrl: './create-file-popup.component.html',
  styleUrls: ['./create-file-popup.component.css']
})
export class CreateFilePopupComponent implements OnInit {

  constructor(private fileService: FileService, public activeModal: NgbActiveModal, private fileMenuComponent: FileMenuComponent) { }

  ngOnInit() {
  }

  saveCreate(name: string){
    this.fileService.createFile(name).subscribe((result: any[]) => {
      this.fileService.getAllFiles().subscribe();
    });
  }


}

import { Component, OnInit } from '@angular/core';
import {FileService} from "../../services/file.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FileOptionsComponent} from '../file-options/file-options.component';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Component({
  selector: 'app-file-menu',
  templateUrl: './file-menu.component.html',
  styleUrls: ['./file-menu.component.css']
})
export class FileMenuComponent implements OnInit {
  files: any[] = [];
  getThis: string;
  public leftSubject: BehaviorSubject<string>;
  constructor(private fileService:FileService, private modalService: NgbModal) {
    this.leftSubject = new BehaviorSubject('');
    this.leftSubject.subscribe(
      newList => {
        this.getThis = newList
      }
    );

  }

  ngOnInit() {
  }

  getAllFiles(){
    this.fileService.getAllFiles().subscribe((obj) => {
      this.files = obj;
    });
  }

  getOwnedFiles(){
    this.fileService.getOwnedFiles().subscribe((obj) => {
      this.files = obj;
    });
    let selections = [];
  }

  getSharedFiles(){
    this.fileService.getSharedFiles().subscribe((obj) => {
      this.files = obj;
    });
  }

  getTrashedFiles(){
    this.fileService.getTrashedFiles().subscribe((obj) => {
      this.files = obj;
    });
  }

  getStarred(){
    this.fileService.getStarred().subscribe((obj) => {
      this.files = obj;
    });
  }

  getImportant(){
    this.fileService.getImportant().subscribe((obj) => {
      this.files = obj;
    });
  }

  getPersonal(){
    this.fileService.getPersonal().subscribe((obj) => {
      this.files = obj;
    });
  }

  getWhat(param: string){
    console.log(param);
    this.leftSubject.next(param);
  }
}

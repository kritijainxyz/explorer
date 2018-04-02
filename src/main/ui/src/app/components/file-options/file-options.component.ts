import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {FileService} from "../../services/file.service";
import {Observable} from "rxjs/Observable";
import "rxjs/add/observable/forkJoin";
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RenamePopupComponent} from "../rename-popup/rename-popup.component";
import {CreateFilePopupComponent} from "../create-file-popup/create-file-popup.component";
import {UploadComponent} from "../upload/upload.component";
import {BehaviorSubject} from "rxjs/BehaviorSubject";
import {FileMenuComponent} from '../file-menu/file-menu.component';
import {ShareComponent} from "../../share/share.component";


//import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-file-options',
  templateUrl: './file-options.component.html',
  styleUrls: ['./file-options.component.css']
})
export class FileOptionsComponent implements OnInit, OnDestroy {
  selections: number[] = [];
  items: any[];
  newFile: any;
  getThis:string;



  constructor(private fileService: FileService, private modalService: NgbModal, private fileMenuComponent: FileMenuComponent) {
    this.fileService.selectionSubject.subscribe(s => {
      this.selections = s;
    });

    fileMenuComponent.leftSubject.subscribe(st => {
      this.getThis = st;
    });


    this.items = [
      "Create",
      "Rename",
      "Create a copy",
      "Move",
      "Move to trash",
      "Delete",
      "Share",
      "Upload",
      "Download"

    ]
  }
  ngOnInit() {
  }

  ngOnDestroy(){
  }

  renamepop() {
    this.modalService.open(RenamePopupComponent);
  }

  createpop(){
    this.modalService.open(CreateFilePopupComponent);
  }


  tagStar() {
    let para: Observable<any>[] = [];
    for (var i = 0; i < this.selections.length; i++) {
      para.push(this.fileService.tagStar(this.selections[i]));
    }
    Observable.forkJoin(para).subscribe((result: any[]) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  tagImportant() {
    let para: Observable<any>[] = [];
    for (var i = 0; i < this.selections.length; i++) {
      para.push(this.fileService.tagImportant(this.selections[i]));
    }
    Observable.forkJoin(para).subscribe((result: any[]) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  tagPersonal() {
    let para: Observable<any>[] = [];
    for (var i = 0; i < this.selections.length; i++) {
      para.push(this.fileService.tagPersonal(this.selections[i]));
    }
    Observable.forkJoin(para).subscribe((result: any[]) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  delete() {
    this.fileService.deleteFile(this.selections).subscribe((result: any) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  trash() {
    let para: Observable<any>[] = [];
    for (var i = 0; i < this.selections.length; i++) {
      para.push(this.fileService.trash(this.selections[i]));
    }
    Observable.forkJoin(para).subscribe((result: any[]) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  restore() {
    let para: Observable<any>[] = [];
    for (var i = 0; i < this.selections.length; i++) {
      para.push(this.fileService.restore(this.selections[i]));
    }
    Observable.forkJoin(para).subscribe((result: any[]) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }

  copy(){

    this.fileService.copy(this.selections[0]).subscribe((result: any) => {
      this.fileService.selectionSubject.next(null);
      this.fileService.getAllFiles().subscribe();
    });
  }
  move(){

  }

  sharepop(){
    this.modalService.open(ShareComponent);
  }

  uploadpop(){
    this.modalService.open(UploadComponent);

  }

  download(){
    //console.log('download clicked');

    this.fileService.download(this.selections[0]);
    this.fileService.getAllFiles().subscribe();
    this.fileService.selectionSubject.next(null);
  }


}

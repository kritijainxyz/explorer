import {Component, OnInit} from '@angular/core';
import {FileService} from "../../services/file.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-rename-popup',
  templateUrl: './rename-popup.component.html',
  styleUrls: ['./rename-popup.component.css']
})
export class RenamePopupComponent implements OnInit {
  selections: number[] = [];

  constructor(private fileService: FileService, public activeModal: NgbActiveModal) {
    this.fileService.selectionSubject.subscribe(s => {
      this.selections = s;
    })
  }

  ngOnInit() {

  }

  saveRename(name: string) {
    this.fileService.rename(this.selections[0],name).subscribe((result: any) => {
      this.fileService.getAllFiles().subscribe();
      this.fileService.selectionSubject.next(null);
    });
  }

}

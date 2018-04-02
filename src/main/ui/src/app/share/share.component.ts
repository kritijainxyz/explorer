import { Component, OnInit } from '@angular/core';
import {FileService} from "../services/file.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-share',
  templateUrl: './share.component.html',
  styleUrls: ['./share.component.css']
})
export class ShareComponent implements OnInit {
  selections: number[] = [];

  constructor(private fileService: FileService, public activeModal: NgbActiveModal) {
    this.fileService.selectionSubject.subscribe(s => {
      this.selections = s;
    });
  }

  ngOnInit() {
  }

  saveShare(userId: number) {
    this.fileService.share(this.selections[0], userId).subscribe((result: any) => {
      this.fileService.getAllFiles().subscribe();
      this.fileService.selectionSubject.next(null);
    });
  }
}


import {Component, OnInit} from '@angular/core';
import {FileService} from "../../services/file.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {


  constructor(public fileService: FileService, public activeModal: NgbActiveModal) {
  }

  submitUpload(file: File) {
    let formData = new FormData();
    formData.set("file", file);
    this.fileService.upload(formData).subscribe((result: any) => {
      this.fileService.getAllFiles().subscribe();
    });
  }

  ngOnInit() {
  }
}



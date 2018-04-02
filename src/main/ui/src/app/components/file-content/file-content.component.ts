import { Component, OnInit } from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FileService} from "../../services/file.service";

@Component({
  selector: 'app-file-content',
  templateUrl: './file-content.component.html',
  styleUrls: ['./file-content.component.css']
})
export class FileContentComponent implements OnInit {
  public static fileName: string;
  public static fileId: number;
  public static fileContent: string;

  constructor(private fileService: FileService, public activeModal: NgbActiveModal) { }

  ngOnInit() {
  }

  getFileName(): string{
    return FileContentComponent.fileName;
  }
  getFileId(): number{
    console.log(FileContentComponent.fileId)
    return FileContentComponent.fileId;
  }

  getFileContent(): string{
    return FileContentComponent.fileContent;
  }

  saveContent(content: string){
     this.fileService.saveContent( this.getFileId(), content).subscribe((result: any) => {
       this.fileService.getAllFiles().subscribe();
     });
  }
}

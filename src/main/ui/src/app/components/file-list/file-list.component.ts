import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {FileService} from "../../services/file.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {FileContentComponent} from "../file-content/file-content.component";

@Component({
  selector: 'app-file-list',
  templateUrl: './file-list.component.html',
  styleUrls: ['./file-list.component.css']
})
export class FileListComponent implements OnInit, OnChanges, OnDestroy {
  files: any[] = [];
  //@Output() onSelectionChange = new EventEmitter<string[]>();
  localSelections = {};
  selections: string[] = [];

  constructor(private fileService: FileService, private modalService: NgbModal) {
    this.fileService.fileSubject.subscribe(
      newFiles => {
        this.files = newFiles
      }
    )

    // this.fileService.selectionSubject.subscribe(
    //   newSelections => {
    //     this.selections = newSelections
    //   }
    // )

  }

  ngOnChanges(changes: SimpleChanges): void {
    //console.log(changes);
    this.selections = null;
    this.localSelections = {};
    this.fileService.selectionSubject.subscribe(selections => {
      if (selections == null) {
        this.clearAllSelections();
      }
    });
  }

  ngOnInit() {
    this.selections = null;
    this.localSelections = {};
    this.fileService.selectionSubject.subscribe(selections => {
      if (selections == null) {
        this.clearAllSelections();
      }
    });
  }

  ngOnDestroy() {
    this.selections = null;
    this.localSelections = {};
    this.fileService.selectionSubject.subscribe(selections => {
      if (selections == null) {
        this.clearAllSelections();
      }
    });
  }

  onItemChecked(e: any, file: any) {
    if (e) {
      this.localSelections[file.fileId] = e;
    } else {
      delete this.localSelections[file.fileId];
    }

    //this.onSelectionChange.emit(Object.keys(this.localSelections));
    this.selections = Object.keys(this.localSelections);
    this.fileService.selectionSubject.next(this.selections);
  }

  editText(fileName: string, fileId: number, fileContent: string) {
    FileContentComponent.fileName = fileName;
    FileContentComponent.fileId = fileId;
    FileContentComponent.fileContent = fileContent;
    //console.log("Content: " + fileContent)
    this.modalService.open(FileContentComponent, {size: 'lg'});
  }

  clearAllSelections(): void {
    this.localSelections = {};
  }
}

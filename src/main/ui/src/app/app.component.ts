import {Component} from '@angular/core';
import {Http} from "@angular/http";
import {FileService} from './services/file.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  files: any[] = [];
  selections: string[] = [];

  /*selection: any[]=[];*/

  constructor(private http: Http, private fileService: FileService) {
  }

  ngOnInit() {
    this.fileService.getAllFiles().subscribe((obj) => {
      this.files = obj;
    });
  }



  onSelectionChange(fileIds: string[]) {
    this.selections = fileIds;
  }
}

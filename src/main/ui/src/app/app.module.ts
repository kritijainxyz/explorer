import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppComponent} from './app.component';
import {FileListComponent} from './components/file-list/file-list.component';
import {FileMenuComponent} from './components/file-menu/file-menu.component';
import {FileOptionsComponent} from './components/file-options/file-options.component';
import {FileService} from "./services/file.service";
import {RenamePopupComponent} from './components/rename-popup/rename-popup.component';
import {CreateFilePopupComponent} from './components/create-file-popup/create-file-popup.component';
import {NgbActiveModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { FileContentComponent } from './components/file-content/file-content.component';
import { UploadComponent } from './components/upload/upload.component';
import { DownloadComponent } from './components/download/download.component';
import { ShareComponent } from './share/share.component';

@NgModule({
  declarations: [
    AppComponent,
    FileListComponent,
    FileMenuComponent,
    FileOptionsComponent,
    RenamePopupComponent,
    CreateFilePopupComponent,
    FileContentComponent,
    UploadComponent,
    DownloadComponent,
    ShareComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    NgbModule.forRoot(),
    HttpModule
  ],
  providers: [FileService, NgbActiveModal, FileMenuComponent],
  bootstrap: [AppComponent],
  entryComponents: [RenamePopupComponent, CreateFilePopupComponent, FileContentComponent, UploadComponent, DownloadComponent, ShareComponent]
})
export class AppModule {
}

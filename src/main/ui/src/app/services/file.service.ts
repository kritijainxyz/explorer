import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import "rxjs/add/operator/map";
import {BehaviorSubject} from "rxjs/BehaviorSubject";

@Injectable()
export class FileService {

  public fileSubject: BehaviorSubject<any[]>;
  public selectionSubject: BehaviorSubject<any[]>;

  constructor(private http: Http) {
    this.fileSubject = new BehaviorSubject([]);
    this.selectionSubject = new BehaviorSubject([]);
  }

  getAllFiles() {
    return this.http.get('files/user/allfiles/1')
      .map(res => {
        res.json();
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files
      });
  }

  getOwnedFiles() {
    return this.http.get('files/user/ownedfiles/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }

  getSharedFiles() {
    return this.http.get('files/user/sharedfiles/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }

  getTrashedFiles() {
    return this.http.get('files/user/searchTrashed/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }

  getStarred() {
    return this.http.get('files/user/searchStarred/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }

  getImportant() {
    return this.http.get('files/user/searchImportant/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }

  getPersonal() {
    return this.http.get('files/user/searchPersonal/1')
      .map(res => {
        let files = res.json() as any[];
        this.fileSubject.next(files);
        return files;
      });
  }


  createFile(name: string) {
    return this.http.post('files/1/filename/' + name, {})
      .map(res => res.json());
  }

  deleteFile(fileIds: number[]) {
    return this.http.patch('files/user/1/delete', fileIds);
  }

  tagStar(fileId: number) {
    return this.http.patch('files/' + fileId + '/user/1/star', {});
  }

  tagImportant(fileId: number) {
    return this.http.patch('files/' + fileId + '/user/1/important', {});
  }

  tagPersonal(fileId: number) {
    return this.http.patch('files/' + fileId + '/user/1/personal', {});
  }

  trash(fileId: number) {
    return this.http.patch('files/' + fileId + '/user/1/trash', {});
  }

  restore(fileId: number) {
    return this.http.patch('files/' + fileId + '/user/1/restore', {});
  }

  rename(fileId: number, newName: string) {
    return this.http.patch('files/' + fileId + '/user/1/rename/' + newName, {});
  }

  copy(fileId: number) {
    return this.http.post('files/' + fileId + '/user/1/copy', {});
  }

  move(fileId: number, parentId: number) {
    return this.http.patch('files/' + fileId + '/user/1/move/' + parentId, {});
  }

  share(fileId: number, sharedUserId: number) {
    return this.http.patch('files/' + fileId + '/user/1/share/' + sharedUserId + '/permission/2',{});
  }

  saveContent(fileId: number, content: string) {
    return this.http.patch('files/' + fileId + '/user/1/saveContent/', content);
  }

  upload(fileForm: FormData) {
    return this.http.post('files/user/1/upload', fileForm);
  }

  download(fileId: number): void {
    //console.log('in download service');

    let url = 'files/' + fileId + '/user/1/download';
    window.location.href = url;
  }


}

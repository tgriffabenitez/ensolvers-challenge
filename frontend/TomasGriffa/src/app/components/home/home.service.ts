import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NoteClass } from './note-class';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  url: string = "https://ensolvers-challenge-production.up.railway.app/"


  public postCreateNote(data: any) {
    return this.http.post(this.url, data);
  }

  public patchUpdateNote(data: any) {
    return this.http.patch(this.url + data.id, data);
  }

  public getActiveNotes(userId: any) {
    return this.http.get(this.url + userId + "/active")
  }

  public deleteNote(note: any) {
    return this.http.delete(this.url + note);
  }

  public patchArchiveNote(note: any) {
    return this.http.patch(this.url + note.id, note);
  }
}

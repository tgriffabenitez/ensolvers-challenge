import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NoteClass } from './note-class';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  public postCreateNote(data: any) {
    return this.http.post('https://ensolvers-challenge-production.up.railway.app/', data);
  }

  public patchUpdateNote(data: any) {
    return this.http.patch("https://ensolvers-challenge-production.up.railway.app/" + data.id, data);
  }

  public getActiveNotes() {
    return this.http.get("https://ensolvers-challenge-production.up.railway.app/")
  }

  public deleteNote(note: any) {
    return this.http.delete("https://ensolvers-challenge-production.up.railway.app/"+ note);
  }

  public patchArchiveNote(note: any) {
    return this.http.patch("https://ensolvers-challenge-production.up.railway.app/" + note.id, note);
  }
}

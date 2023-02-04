import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { NoteClass } from './note-class';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private http: HttpClient) { }

  public postCreateNote(data: any) {
    return this.http.post('http://localhost:8080/', data);
  }

  public patchUpdateNote(data: any) {
    return this.http.patch("http://localhost:8080/" + data.id, data);
  }

  public getActiveNotes() {
    return this.http.get("http://localhost:8080/")
  }

  public deleteNote(note: any) {
    return this.http.delete("http://localhost:8080/"+ note);
  }

  public patchArchiveNote(note: any) {
    return this.http.patch("http://localhost:8080/" + note.id, note);
  }
}

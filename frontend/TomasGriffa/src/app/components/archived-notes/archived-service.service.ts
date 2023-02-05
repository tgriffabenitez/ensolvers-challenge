import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArchivedServiceService {

  constructor(private http: HttpClient) { }

  public getArchivedNotes() {
    return this.http.get("https://ensolvers-challenge-production.up.railway.app/archived/")
  }

  public patchRestoreNote(note: any) {
    return this.http.patch("https://ensolvers-challenge-production.up.railway.app/" + note.id, note);
  }

  public deleteNote(note: any) {
    return this.http.delete("https://ensolvers-challenge-production.up.railway.app/"+ note);
  }


}

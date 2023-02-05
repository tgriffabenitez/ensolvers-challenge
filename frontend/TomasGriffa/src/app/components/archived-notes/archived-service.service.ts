import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArchivedServiceService {

  constructor(private http: HttpClient) { }

  url: string = "https://ensolvers-challenge-production.up.railway.app/"

  public getArchivedNotes(userId: number) {
    return this.http.get(this.url + userId + "/archived")
  }

  public patchRestoreNote(note: any) {
    return this.http.patch(this.url + note.id, note);
  }

  public deleteNote(note: any) {
    return this.http.delete(this.url + note);
  }


}

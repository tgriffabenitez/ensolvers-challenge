import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ArchivedServiceService {

  constructor(private http: HttpClient) { }

  public getArchivedNotes(userId: number) {
    return this.http.get("http://localhost:8080/" + userId + "/archived")
  }

  public patchRestoreNote(note: any) {
    return this.http.patch("http://localhost:8080/" + note.id, note);
  }

  public deleteNote(note: any) {
    return this.http.delete("http://localhost:8080/"+ note);
  }


}

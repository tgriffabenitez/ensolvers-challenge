import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NoteClass } from '../home/note-class';
import { ArchivedServiceService } from './archived-service.service';

@Component({
  selector: 'app-archived-notes',
  templateUrl: './archived-notes.component.html',
  styleUrls: ['./archived-notes.component.css']
})
export class ArchivedNotesComponent {

  public archivedNotes: any;

  constructor (private formBuilder: FormBuilder, private archivedService: ArchivedServiceService) { }

  ngOnInit(): void {
  
    this.getArchivedNotes();

  }

  getArchivedNotes() {
    this.archivedService.getArchivedNotes().subscribe((data: any) => {
      this.archivedNotes = data;
    });
  }

  restoreNote(note: NoteClass) {

    let data = {
      id: note.id,
      title: '',
      note: '',
      isActive: true
    }

    console.log(data);

    this.archivedService.patchRestoreNote(data).subscribe((res: any) => {
      console.log(res);
      this.getArchivedNotes();      
    })
  }

  deleteNote(note: NoteClass) {

    let id = note.id;

    this.archivedService.deleteNote(id).subscribe(res => {

    }, (err: any) => {
      if (err.status == 200) {
        this.getArchivedNotes();
      }
    });

  }



}

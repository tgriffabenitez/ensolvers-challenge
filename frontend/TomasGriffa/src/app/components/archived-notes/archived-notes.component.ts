import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { NoteClass } from '../home/note-class';
import { ArchivedServiceService } from './archived-service.service';

@Component({
  selector: 'app-archived-notes',
  templateUrl: './archived-notes.component.html',
  styleUrls: ['./archived-notes.component.css']
})
export class ArchivedNotesComponent {

  userId!: number;

  public archivedNotes: any;

  constructor (private formBuilder: FormBuilder, private archivedService: ArchivedServiceService, private router: Router) { }

  ngOnInit(): void {

    if (localStorage.getItem("userId") == null) {
      this.router.navigate(['']);

    } else {
      this.userId = parseInt(localStorage.getItem("userId")!, 10)
    }
  
    this.getArchivedNotes();

  }

  getArchivedNotes() {
    this.archivedService.getArchivedNotes(this.userId).subscribe((data: any) => {
      this.archivedNotes = data;
    });

    if (localStorage.getItem("userId") == null) {
      this.router.navigate(['']);
  
    }
  }

  restoreNote(note: NoteClass) {

    let data = {
      id: note.id,
      title: '',
      note: '',
      isActive: true
    }

    this.archivedService.patchRestoreNote(data).subscribe((res: any) => {
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

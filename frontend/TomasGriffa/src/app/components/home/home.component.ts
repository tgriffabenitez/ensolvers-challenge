import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { HomeService } from './home.service';
import { NoteClass } from './note-class';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  userId!: number;

  noteDetail !: FormGroup;
  noteObj: NoteClass = new NoteClass();


  activeNotes: any;
  archivedNotes: any;


  constructor (private formBuilder: FormBuilder, private homeService: HomeService, private router: Router) { }

  ngOnInit(): void {

    if (localStorage.getItem("userId") == null) {
      this.router.navigate(['']);

    } else {
      this.userId = parseInt(localStorage.getItem("userId")!, 10)
    }

    this.noteDetail = this.formBuilder.group({
      id: [''],
      title: [''],
      note: [''],
      isActive: ['']
    });

  this.getActiveNotes();

  }

  createNote() {

    let data = {
      title: this.noteDetail.value.title,
      note: this.noteDetail.value.note,
      userId: this.userId
    }


    this.homeService.postCreateNote(data).subscribe(res => {
      this.getActiveNotes();
      this.resetForm();
      
    }, err =>  {

    });
  }

  archiveNote(note: NoteClass) {

    let data = {
      id: note.id,
      title: '',
      note: '',
      isActive: false
    }

    this.homeService.patchArchiveNote(data).subscribe((res: any) => {
      this.getActiveNotes();      
    })
  }

  getActiveNotes() {
    this.homeService.getActiveNotes(this.userId).subscribe((data: any) => {
      this.activeNotes = data;
    });
  }

  editNote(note: NoteClass) {
    this.noteDetail.controls["id"].setValue(note.id);
    this.noteDetail.controls["title"].setValue(note.title);
    this.noteDetail.controls["note"].setValue(note.note);
    this.noteDetail.controls["isActive"].setValue(note.isActive);
  }

  updateNote() {
    this.noteObj.id = this.noteDetail.value.id;
    this.noteObj.title = this.noteDetail.value.title;
    this.noteObj.note = this.noteDetail.value.note;
    this.noteObj.isActive = this.noteDetail.value.isActive;

    this.homeService.patchUpdateNote(this.noteObj).subscribe(res => {
      this.getActiveNotes();

    }, err => {
    });
  }

  deleteNote(note: NoteClass) {

    let id = note.id;

      if(confirm("Are you sure to delete: " + note.title)) {
      this.homeService.deleteNote(id).subscribe(res => {

      }, (err: any) => {
        if (err.status == 200) {
          this.getActiveNotes();
        }
      });
    }

  }

  resetForm() {
    this.noteDetail.reset();
  }

}

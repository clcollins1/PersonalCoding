import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-compare-component',
  templateUrl: './compare-component.component.html',
  styleUrls: ['./compare-component.component.css']
})
export class CompareComponent implements OnInit {

  serverUrl = 'http://localhost:8080/compare/compareFaces';
  url: string;
  url2: string;
  myForm: FormGroup;
  likeness;
  title = 'LikenessPercentageGenerator';

  constructor(private httpClient: HttpClient, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.myForm = this.formBuilder.group({
      file1: [''],
      file2: ['']
    });
  }

  onSubmit() {
    const formData = new FormData();
    console.log(this.myForm.value);

    formData.append('file1', this.myForm.get('file1').value);
    formData.append('file2', this.myForm.get('file2').value);
    this.httpClient.post(this.serverUrl, formData).subscribe (
      (result) => {
      this.likeness = result;
    }, (error) => {
      console.log(error);
      }

    );
  }
  onReset() {
    this.myForm.reset({file1: '', file2: ''});
    this.url = '';
    this.url2 = '';
  }

  showPreview1(event) {
    const file = event.target.files[0];
    this.myForm.get('file1').setValue(file);
    const reader = new FileReader();
    reader.onload = () => {
      this.url = reader.result as string;
    };
    reader.readAsDataURL(file);
  }
  showPreview2(event) {
    const file = event.target.files[0];
    this.myForm.get('file2').setValue(file);
    const reader = new FileReader();
    reader.onload = () => {
      this.url2 = reader.result as string;
    };
    reader.readAsDataURL(file);
  }
}

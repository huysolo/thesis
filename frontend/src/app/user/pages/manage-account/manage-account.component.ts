import { Component, OnInit } from '@angular/core';
import {AuthService} from '../../../core/auth.service';
import {CurrUserInfo} from '../login/curr-user-info';
import { ReactiveFormsModule } from '@angular/forms';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-manage-account',
  templateUrl: './manage-account.component.html',
  styleUrls: ['./manage-account.component.css']
})
export class ManageAccountComponent implements OnInit {
  currUserInfo: CurrUserInfo;
  profileForm: FormGroup;
  constructor(public authService: AuthService, private fb: FormBuilder) {
    this.profileForm = this.fb.group({
      username: [this.authService.getUsername(), Validators.required ],
      firstname:[this.authService.getFirstname(), Validators.required],
      lastname:[this.authService.getLastname(),  Validators.required],
      email:[this.authService.getEmail(), Validators.required],
      gender:[this.authService.getGender(),  Validators.required],
      skills:[this.authService.getSkills(),  Validators.required],
      degree:[this.authService.getDegree(),  Validators.required],
      profID:[this.authService.getProfID(), Validators.required]
    });
  }

  ngOnInit() {
  }

  edit(form){
    console.log(form.username);
  }

}

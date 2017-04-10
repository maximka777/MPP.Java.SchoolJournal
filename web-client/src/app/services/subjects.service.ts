import {Injectable, Inject} from "@angular/core";
import {ReplaySubject} from "rxjs";
import {Http, URLSearchParams} from "@angular/http";
import {APP_CONFIG} from "../configs/app.config";
import {AuthService} from "./auth.service";


@Injectable()
export class SubjectsService {

  subjects = [];
  subjectsSubject: ReplaySubject<any> = new ReplaySubject(1);

  constructor(@Inject(APP_CONFIG) private config: any,
              private http: Http,
              private authService: AuthService){
    this.fetchSubjects();
  }

  fetchSubjects() {
    return new Promise((resolve, reject) => {
      let params = new URLSearchParams();
      params.append('token', this.authService.token);
      this.http.get(`${this.config.apiEndpoint}/subjects`, {search: params})
        .map(res => res.json())
        .subscribe((subjects) => {
          this.subjects = subjects;
          this.subjectsSubject.next(subjects);
          resolve(subjects);
        });
    });
  }

}
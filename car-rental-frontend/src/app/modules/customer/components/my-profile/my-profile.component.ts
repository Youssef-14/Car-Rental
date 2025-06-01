import { Component } from '@angular/core';
import {NzDescriptionsComponent, NzDescriptionsItemComponent} from 'ng-zorro-antd/descriptions';
import {NzDividerComponent} from 'ng-zorro-antd/divider';

@Component({
  selector: 'app-my-profile',
  imports: [
    NzDescriptionsComponent,
    NzDescriptionsItemComponent,
    NzDividerComponent
  ],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.scss'
})
export class MyProfileComponent {

}

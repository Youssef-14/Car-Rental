import { Component, OnInit } from '@angular/core';
import {NzDescriptionsComponent, NzDescriptionsItemComponent, NzDescriptionsModule} from 'ng-zorro-antd/descriptions';
import {NzDividerComponent, NzDividerModule} from 'ng-zorro-antd/divider';
import {NzDrawerComponent, NzDrawerContentDirective, NzDrawerModule} from 'ng-zorro-antd/drawer';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { DateUtils } from "../../../../shared/utils/date-util";

import {
  NzTableFilterFn,
  NzTableFilterList,
  NzTableModule,
  NzTableSortFn,
  NzTableSortOrder
} from 'ng-zorro-antd/table';
import { User } from '../../../../models/user';
import {AdminService} from '../../services/admin.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import {DatePipe} from '@angular/common';


interface ColumnItem {
  name: string;
  sortOrder: NzTableSortOrder | null;
  sortFn: NzTableSortFn<User> | null;
  listOfFilter: NzTableFilterList;
  filterFn: NzTableFilterFn<User> | null;
}

@Component({
  selector: 'app-get-users',
  imports: [NzButtonModule, NzTableModule, NzDescriptionsComponent, NzDescriptionsItemComponent, NzDrawerComponent, NzDividerComponent, NzDrawerContentDirective, DatePipe],
  styleUrl: './get-users.component.scss',
  templateUrl: './get-users.component.html',
})
export class GetUsersComponent{
  listOfData: User[] = [ ];
  constructor(private adminService: AdminService,
              private message: NzMessageService) {
    this.getAllUsers();
  }

  getAllUsers() {
    this.adminService.getAllUser().subscribe(res => {
      res.forEach((user: User) => {
        this.listOfData.push(user);
      });
    });
  }

  listOfColumns: ColumnItem[] = [
    {
      name: 'Email',
      sortOrder: null,
      sortFn: (a: User, b: User) => a.email.localeCompare(b.email),
      listOfFilter: [],
      filterFn: (list: string[], item: User) => list.some(name => item.email.indexOf(name) !== -1)
    },
    {
      name: 'Number',
      sortOrder: null,
      sortFn: null,
      listOfFilter: [],
      filterFn: null
    },
    {
      name: 'Date Inscription',
      sortFn: null,
      sortOrder: null,
      listOfFilter: [
      ],
      filterFn: null
    },
    {
      name: 'Voir',
      sortOrder: null,
      sortFn: null,
      listOfFilter: [],
      filterFn: null
    }
  ];

  sortByAge(): void {
    this.listOfColumns.forEach(item => {
      if (item.name === 'Age') {
        item.sortOrder = 'descend';
      } else {
        item.sortOrder = null;
      }
    });
  }

  resetFilters(): void {
    this.listOfColumns.forEach(item => {
      if (item.name === 'Name') {
        item.listOfFilter = [
          { text: 'Joe', value: 'Joe' },
          { text: 'Jim', value: 'Jim' }
        ];
      } else if (item.name === 'Address') {
        item.listOfFilter = [
          { text: 'London', value: 'London' },
          { text: 'Sidney', value: 'Sidney' }
        ];
      }
    });
  }

  resetSortAndFilters(): void {
    this.listOfColumns.forEach(item => {
      item.sortOrder = null;
    });
    this.resetFilters();
  }

  visible = false;

  open(): void {
    this.visible = true;
  }

  close(): void {
    this.visible = false;
  }
}


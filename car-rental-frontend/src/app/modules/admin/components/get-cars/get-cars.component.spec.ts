import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCarsComponent } from './get-cars.component';

describe('GetCarsComponent', () => {
  let component: GetCarsComponent;
  let fixture: ComponentFixture<GetCarsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetCarsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

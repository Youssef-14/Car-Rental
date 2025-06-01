import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCarBookingsComponent } from './get-car-bookings.component';

describe('GetCarBookingsComponent', () => {
  let component: GetCarBookingsComponent;
  let fixture: ComponentFixture<GetCarBookingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetCarBookingsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetCarBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

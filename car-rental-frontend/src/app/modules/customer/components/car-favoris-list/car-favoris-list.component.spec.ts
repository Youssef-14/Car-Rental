import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarFavorisListComponent } from './car-favoris-list.component';

describe('CarFavorisListComponent', () => {
  let component: CarFavorisListComponent;
  let fixture: ComponentFixture<CarFavorisListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarFavorisListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarFavorisListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

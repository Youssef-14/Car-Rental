import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetReclamationsComponent } from './get-reclamations.component';

describe('GetReclamationsComponent', () => {
  let component: GetReclamationsComponent;
  let fixture: ComponentFixture<GetReclamationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetReclamationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GetReclamationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

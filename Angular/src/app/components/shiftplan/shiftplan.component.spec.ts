import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShiftPlanComponent } from './ShiftPlan.component';

describe('ShiftplanComponent', () => {
  let component: ShiftPlanComponent;
  let fixture: ComponentFixture<ShiftPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShiftPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ShiftDayComponent} from './ShiftDay.component';

describe('ShiftDayComponent', () => {
  let component: ShiftDayComponent;
  let fixture: ComponentFixture<ShiftDayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShiftDayComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

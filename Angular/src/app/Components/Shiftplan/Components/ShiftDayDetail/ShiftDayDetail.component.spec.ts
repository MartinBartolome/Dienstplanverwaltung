import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ShiftDayDetailComponent} from './ShiftDayDetail.component';

describe('ShiftDayDetailComponent', () => {
  let component: ShiftDayDetailComponent;
  let fixture: ComponentFixture<ShiftDayDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShiftDayDetailComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftDayDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

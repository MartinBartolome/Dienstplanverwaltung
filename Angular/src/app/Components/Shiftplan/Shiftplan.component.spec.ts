import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ShiftplanComponent} from './Shiftplan.component';

describe('ShiftplanComponent', () => {
  let component: ShiftplanComponent;
  let fixture: ComponentFixture<ShiftplanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShiftplanComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftplanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

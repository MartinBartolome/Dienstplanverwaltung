import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoubleTableComponent } from './DoubleTable.component';

describe('DoubleTableComponent', () => {
  let component: DoubleTableComponent;
  let fixture: ComponentFixture<DoubleTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DoubleTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DoubleTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

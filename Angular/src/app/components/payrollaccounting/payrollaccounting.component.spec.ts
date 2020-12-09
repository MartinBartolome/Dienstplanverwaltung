import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollAccountingComponent } from './PayrollAccounting.component';

describe('PayrollaccountingComponent', () => {
  let component: PayrollAccountingComponent;
  let fixture: ComponentFixture<PayrollAccountingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayrollAccountingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayrollAccountingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

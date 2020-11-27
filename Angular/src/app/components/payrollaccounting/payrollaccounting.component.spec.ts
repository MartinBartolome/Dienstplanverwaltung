import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayrollaccountingComponent } from './payrollaccounting.component';

describe('PayrollaccountingComponent', () => {
  let component: PayrollaccountingComponent;
  let fixture: ComponentFixture<PayrollaccountingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayrollaccountingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayrollaccountingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

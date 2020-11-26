import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeesConfigComponent } from './employees-config.component';

describe('EmployeesConfigComponent', () => {
  let component: EmployeesConfigComponent;
  let fixture: ComponentFixture<EmployeesConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeesConfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeesConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

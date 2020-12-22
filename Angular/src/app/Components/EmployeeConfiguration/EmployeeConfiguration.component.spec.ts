import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeConfigurationComponent} from './EmployeeConfiguration.component';

describe('EmployeeConfigurationComponent', () => {
  let component: EmployeeConfigurationComponent;
  let fixture: ComponentFixture<EmployeeConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeConfigurationComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

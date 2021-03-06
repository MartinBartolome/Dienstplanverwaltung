import {ComponentFixture, TestBed} from '@angular/core/testing';

import {EmployeeConfigComponent} from './EmployeeConfig.component';

describe('EmployeeConfigComponent', () => {
  let component: EmployeeConfigComponent;
  let fixture: ComponentFixture<EmployeeConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployeeConfigComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeInviteComponent } from './employee-invite.component';

describe('EmployeeInviteComponent', () => {
  let component: EmployeeInviteComponent;
  let fixture: ComponentFixture<EmployeeInviteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeInviteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeInviteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

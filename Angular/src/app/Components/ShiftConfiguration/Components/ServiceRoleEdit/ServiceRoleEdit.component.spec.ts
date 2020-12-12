import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceRoleEditComponent } from './ServiceRoleEdit.component';

describe('ServiceRoleEditComponent', () => {
  let component: ServiceRoleEditComponent;
  let fixture: ComponentFixture<ServiceRoleEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ServiceRoleEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ServiceRoleEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

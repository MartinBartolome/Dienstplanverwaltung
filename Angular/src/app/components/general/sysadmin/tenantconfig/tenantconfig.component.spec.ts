import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantconfigComponent } from './tenantconfig.component';

describe('TenantconfigComponent', () => {
  let component: TenantconfigComponent;
  let fixture: ComponentFixture<TenantconfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TenantconfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantconfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

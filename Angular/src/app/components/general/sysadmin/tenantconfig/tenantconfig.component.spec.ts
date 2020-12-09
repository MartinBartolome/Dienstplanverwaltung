import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TenantConfigComponent } from './TenantConfig.component';

describe('TenantconfigComponent', () => {
  let component: TenantConfigComponent;
  let fixture: ComponentFixture<TenantConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TenantConfigComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TenantConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

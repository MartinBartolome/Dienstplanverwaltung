import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalConfigurationComponent } from './local-configuration.component';

describe('LocalConfigurationComponent', () => {
  let component: LocalConfigurationComponent;
  let fixture: ComponentFixture<LocalConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LocalConfigurationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

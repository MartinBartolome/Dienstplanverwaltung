import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShiftConfigurationComponent } from './ShiftConfiguration.component';

describe('ShiftConfigurationComponent', () => {
  let component: ShiftConfigurationComponent;
  let fixture: ComponentFixture<ShiftConfigurationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShiftConfigurationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShiftConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

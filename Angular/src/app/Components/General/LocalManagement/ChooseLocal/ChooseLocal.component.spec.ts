import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooselocalComponent } from './ChooseLocal.component';

describe('ChooselocalComponent', () => {
  let component: ChooselocalComponent;
  let fixture: ComponentFixture<ChooselocalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChooselocalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooselocalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

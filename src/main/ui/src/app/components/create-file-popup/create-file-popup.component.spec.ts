import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateFilePopupComponent } from './create-file-popup.component';

describe('CreateFilePopupComponent', () => {
  let component: CreateFilePopupComponent;
  let fixture: ComponentFixture<CreateFilePopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateFilePopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateFilePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

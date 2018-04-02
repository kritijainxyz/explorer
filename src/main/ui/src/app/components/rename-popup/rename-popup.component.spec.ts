import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RenamePopupComponent } from './rename-popup.component';

describe('RenamePopupComponent', () => {
  let component: RenamePopupComponent;
  let fixture: ComponentFixture<RenamePopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RenamePopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RenamePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});

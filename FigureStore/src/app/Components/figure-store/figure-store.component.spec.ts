import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FigureStoreComponent } from './figure-store.component';

describe('FigureStoreComponent', () => {
  let component: FigureStoreComponent;
  let fixture: ComponentFixture<FigureStoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FigureStoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FigureStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

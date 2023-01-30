import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FooterComponentApp } from './footer.component';

describe('FooterComponent', () => {
  let component: FooterComponentApp;
  let fixture: ComponentFixture<FooterComponentApp>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FooterComponentApp ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FooterComponentApp);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

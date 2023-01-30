import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponent } from './register.component';
import { RegisterService } from './service/register.service';

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;
  let mockregisterService:any;

  beforeEach(async () => {
    mockregisterService = jasmine.createSpyObj(RegisterService,['']);
    await TestBed.configureTestingModule({
      declarations: [ RegisterComponent ],
      providers:[
        {
          provide:RegisterService,
          useValue:mockregisterService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppService } from 'src/app/app.service';

import { ProfileComponent } from './profile.component';
import { ProfileServiceService } from './service/profile-service.service';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let mockappService:any;
  let mockprofileService:any;

  beforeEach(async () => {
    mockappService = jasmine.createSpyObj(AppService,['']);
    mockappService = jasmine.createSpyObj(ProfileServiceService,['']);
    await TestBed.configureTestingModule({
      declarations: [ ProfileComponent ],
      providers:[
        {
          provide:AppService,
          useValue:mockappService
        },
        {
          provide:ProfileServiceService,
          useValue:mockprofileService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

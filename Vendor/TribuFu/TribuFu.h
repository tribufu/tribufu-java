// Copyright (c) TribuFu. All Rights Reserved

#pragma once

#include <stdarg.h>
#include <stdbool.h>
#include <stddef.h>
#include <stdint.h>
#include <stdlib.h>

#define VERSION "0.0.1"

#ifdef __cplusplus
namespace TribuFu
{
#endif

    enum Role
    {
        User,
        Bot,
        Helper,
        Tester,
        Developer,
        Moderator,
        Admin,
        Root,
    };

    enum Status
    {
        Offline,
        Away,
        Disturb,
        Online,
        Playing,
    };

    enum Theme
    {
        Light,
        Dark,
    };

    struct Email
    {
        const char *Address;
        bool Confirmed;
        bool Primary;
    };

    struct User
    {
        enum Role Role;
        bool Confirmed;
        bool TwoFactorEnabled;
        bool Verified;
        bool ParentalControl;
        bool PublicBirthday;
        enum Theme Theme;
        uint32_t FailedLogins;
        uint32_t ProfileViews;
        uint32_t Content;
        enum Status Status;
        uint32_t Warnings;
    };

#ifdef __cplusplus
    extern "C"
    {
#endif

        void AcceptFriend(char *id1, char *id2);

        void AddFriend(char *id1, char *id2);

        /**
         * Dealloc a email pointer.
         */
        void DeallocEmail(struct Email *email);

        /**
         * Dealloc a string pointer.
         */
        void DeallocString(char *ptr);

        /**
         * Dealloc a user pointer.
         */
        void DeallocUser(struct User *user);

        void EnterTeam(void);

        void GetDevice(char *id);

        /**
         * Get email data from address.
         */
        struct Email *GetEmail(char *address);

        void GetFriends(char *id);

        void GetMessage(char *id);

        void GetRole(char *id);

        /**
         * Get user data from id.
         */
        struct User *GetUser(char *id);

        void GetUserDevices(char *id);

        void GetUserEmails(char *id);

        void GetUserRoles(char *id);

        char *Hello(char *input);

        void InviteToTeam(void);

        struct User *Login(char *name, char *password);

        void Logout(void);

        void Refresh(void);

        void Register(char *name, char *email, char *password);

#ifdef __cplusplus
    }
#endif

#ifdef __cplusplus
}
#endif

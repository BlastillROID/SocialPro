<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Mails
 *
 * @ORM\Table(name="mails")
 * @ORM\Entity
 */
class Mails
{
    /**
     * @var integer
     *
     * @ORM\Column(name="mail_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $mailId;

    /**
     * @var string
     *
     * @ORM\Column(name="content", type="text", length=65535, nullable=false)
     */
    private $content;

    /**
     * @var string
     *
     * @ORM\Column(name="attachement", type="blob", length=65535, nullable=true)
     */
    private $attachement;

    /**
     * @var string
     *
     * @ORM\Column(name="subject", type="string", length=5000, nullable=false)
     */
    private $subject;

    /**
     * @var integer
     *
     * @ORM\Column(name="is_read", type="integer", nullable=false)
     */
    private $isRead;


}

